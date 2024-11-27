package study.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.repository.RegionRepository.RegionRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class RegionQueryServiceImpl implements RegionQueryService {
    private final RegionRepository regionRepository;
    @Override
    public boolean isValid(Long id) {
        return regionRepository.existsById(id);
    }
}
